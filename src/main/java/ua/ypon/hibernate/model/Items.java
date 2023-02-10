package ua.ypon.hibernate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Items")
public class Items {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_items")
    private String nameItems;

    @ManyToOne
    @JoinColumn(name = "id_items", referencedColumnName = "id")
    private Person owner;

    public Items() {
    }

    public Items(String nameItems) {
        this.nameItems = nameItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_items() {
        return nameItems;
    }

    public void setName_items(String name_items) {
        this.nameItems = nameItems;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name_items='" + nameItems + '\'' +
                ", owner=" + owner +
                '}';
    }
}
