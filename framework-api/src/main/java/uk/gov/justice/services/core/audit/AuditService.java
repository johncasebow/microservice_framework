package uk.gov.justice.services.core.audit;

import uk.gov.justice.services.messaging.JsonEnvelope;

public interface AuditService {

    /**
     * Orchestrates the auditing of the action, uses a blacklist regex pattern to skip auditing if
     * required.
     *
     * @param envelope - the envelope to be audited.
     */
    void audit(final JsonEnvelope envelope);
}
