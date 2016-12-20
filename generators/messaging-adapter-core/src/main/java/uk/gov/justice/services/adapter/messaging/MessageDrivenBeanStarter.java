package uk.gov.justice.services.adapter.messaging;

import static java.lang.String.format;
import static org.jboss.as.controller.client.helpers.ClientConstants.OP;
import static org.jboss.as.controller.client.helpers.ClientConstants.OP_ADDR;
import static org.jboss.as.controller.client.helpers.ClientConstants.OUTCOME;
import static org.jboss.as.controller.client.helpers.ClientConstants.RESULT;

import uk.gov.justice.services.common.configuration.ServiceContextNameProvider;

import java.io.IOException;
import java.net.InetAddress;

import javax.inject.Inject;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.slf4j.Logger;

//@Startup
//@Singleton
public class MessageDrivenBeanStarter {

    @Inject
    Logger logger;

    @Inject
    ServiceContextNameProvider serviceContextNameProvider;

//    @PostConstruct
    public void initialise() {
        final String contextName = serviceContextNameProvider.getServiceContextName();

        logger.info(format("Trying to start message driven beans using Wildfly operation for %s.", contextName));

        try (final ModelControllerClient managementClient = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9990);) {

            final ModelNode operation = new ModelNode();
            operation.get(OP_ADDR).add("deployment", contextName + ".war");
            operation.get(OP_ADDR).add("subsystem", "ejb3");
            operation.get(OP_ADDR).add("message-driven-bean", "*");

            operation.get(OP).set("start-delivery");
            final ModelNode result = managementClient.execute(operation);

            logger.info(format("Message driven beans result %s for %s.", result.toJSONString(false), contextName));

            if (!result.hasDefined(OUTCOME) || (result.hasDefined(OUTCOME) && !result.get(RESULT).asBoolean())) {
                logger.error(format("Unable to start message driven beans, operation failed for %s.", contextName));
            } else {
                logger.info(format("Message driven beans started for %s.", contextName));
            }

        } catch (IOException e) {
            logger.error(format("Unable to connect to Wildfly container for %s.", contextName), e);
        }
    }


}
