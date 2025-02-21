package com.ndhuy.authen.users.domain;

import com.ndhuy.authen.users.domain.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "a_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @EmbeddedId
    @AttributeOverride(name = "username", column = @Column(name = "username"))
    UserNameId usernameId;

    @Column(name = "uuid", unique = true, nullable = false)
    UUID uuid;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    Password password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    Email email;

    @Enumerated
    @AttributeOverride(name = "value", column = @Column(name = "status"))
    NormarlClose status;

    String avatar;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "phone"))
    Phone phone;

    String address;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    Name fullName;

    @Version
    private Long version;

    private User(UserNameId usernameId, Password password, Email email, String avatar, Phone phone, String address,
            Name fullName) {
        this.uuid = UUID.randomUUID();
        this.usernameId = usernameId;
        this.password = password;
        this.email = email;
        this.status = NormarlClose.NORMARL;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.fullName = fullName;
    }

    public static User create(String username, String password, String email, String avatar, String phone,
            String address, String fullName) {
        return new User(UserNameId.generate(username), new Password(password), new Email(email),
                avatar, new Phone(phone), address, new Name(fullName));
    }

}
