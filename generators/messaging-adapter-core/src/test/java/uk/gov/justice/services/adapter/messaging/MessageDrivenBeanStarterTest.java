package uk.gov.justice.services.adapter.messaging;

import static org.mockito.Mockito.when;

import uk.gov.justice.services.common.configuration.ServiceContextNameProvider;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class MessageDrivenBeanStarterTest {

    @Mock
    private Logger logger;

    @Mock
    private ServiceContextNameProvider serviceContextNameProvider;

    @InjectMocks
    private MessageDrivenBeanStarter messageDrivenBeanStarter;

    @Ignore
    @Test
    public void shouldStartAllMessageDrivenBeans() throws Exception {
        when(serviceContextNameProvider.getServiceContextName())
                .thenReturn("example-event-listener")
                .thenReturn("example-event-processor")
                .thenReturn("example-command-handler")
                .thenReturn("example-command-controller");

        messageDrivenBeanStarter.initialise();
        messageDrivenBeanStarter.initialise();
        messageDrivenBeanStarter.initialise();
        messageDrivenBeanStarter.initialise();
    }

}