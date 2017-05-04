package de.fraunhofer.abm.collection.dao.jpa;

import static javax.persistence.FetchType.LAZY;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import de.fraunhofer.abm.domain.CollectionDTO;

@Entity(name="collection")
public class JpaCollection {

    @Id
    @Column
    public String id;

    @Column
    public String user;

    @Column
    public String name;

    @Column
    public String description;

    @OneToMany(fetch=LAZY, mappedBy="collection", cascade=CascadeType.ALL)
    @OrderBy("number")
    public List<JpaVersion> versions;

    public static JpaCollection fromDTO(CollectionDTO dto) {
        JpaCollection collection = new JpaCollection();
        collection.id = dto.id;
        collection.user = dto.user;
        collection.name = dto.name;
        collection.description = dto.description;
        collection.versions = dto.versions.stream()
                .map(JpaVersion::fromDTO)
                .map(version -> {
                    version.collection = collection;
                    return version;
                })
                .collect(Collectors.toList());
        return collection;
    }

    public CollectionDTO toDTO() {
        CollectionDTO collection = new CollectionDTO();
        collection.id = this.id;
        collection.user = this.user;
        collection.name = this.name;
        collection.description = this.description;
        collection.versions = this.versions.stream()
                .map(JpaVersion::toDTO)
                .map(version -> {
                    version.collectionId = collection.id;
                    return version;
                })
                .collect(Collectors.toList());
        return collection;
    }
}
