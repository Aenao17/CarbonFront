package org.example.Domain;

/**
 * Generic interface for entities with an ID.
 *
 * @param <ID> the type of the identifier
 */
public interface Entity<ID> {
    /**
     * Sets the ID of the entity.
     *
     * @param id the ID to set
     */
    void setId(ID id);

    /**
     * Gets the ID of the entity.
     *
     * @return the ID of the entity
     */
    ID getId();
}
