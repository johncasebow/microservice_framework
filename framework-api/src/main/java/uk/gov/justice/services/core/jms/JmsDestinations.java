package uk.gov.justice.services.core.jms;

import uk.gov.justice.services.core.annotation.Component;

import javax.jms.Destination;

public interface JmsDestinations {
    Destination getDestination(final Component component, final String contextName);
}
