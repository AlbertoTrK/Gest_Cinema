package org.alberto.cinema.model;

import jakarta.persistence.Column;

public abstract class BaseEntity {
	
	@Column(nullable = false, columnDefinition = "BIT default 0")
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
