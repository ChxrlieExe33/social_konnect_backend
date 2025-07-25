package com.cdcrane.social_konnect_backend.users;

import com.cdcrane.social_konnect_backend.posts.Post;
import com.cdcrane.social_konnect_backend.roles.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Need this for JPA auditing, allows the @CreatedDate annotation to work.
@Table(name = "users")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean enabled;

    private String email;

    private String bio;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties("users")
    private List<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Post> posts;

    @Column(name = "verification_code", nullable = true)
    private Integer verificationCode; // Must be Integer not int, since it can be null, int cannot contain null, if you try read from db to user, it will cause problem.

}
