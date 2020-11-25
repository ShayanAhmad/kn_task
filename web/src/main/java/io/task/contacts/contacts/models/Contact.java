package io.task.contacts.contacts.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The model for containing the attributes of a contact.
 */
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    private Contact() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return new EqualsBuilder()
                .append(getId(), contact.getId())
                .append(getName(), contact.getName())
                .append(getImageUrl(), contact.getImageUrl())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getImageUrl())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("imageUrl", imageUrl)
                .toString();
    }

    public static final class ContactBuilder {
        private Long id;
        private String name;
        private String imageUrl;

        private ContactBuilder() {
        }

        public static ContactBuilder contactBuilder() {
            return new ContactBuilder();
        }

        public ContactBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ContactBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ContactBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Contact build() {
            Contact contact = new Contact();
            contact.imageUrl = this.imageUrl;
            contact.id = this.id;
            contact.name = this.name;
            return contact;
        }
    }
}
