package com.at.t.eCommerce.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.at.t.eCommerce.enums.AccountStatus;
import com.at.t.eCommerce.enums.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "core_users")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(columnName = "is_deleted")


//HAVE TO CHECK WHY WE HAVE TO USE EAGER FOR SOFTDELETE

public class CoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    private boolean emailVerified;

    @Column(nullable = false, unique = true, updatable = false)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
    private String phone;

    private boolean phoneVerified;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>(Set.of(Role.BUYER));

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    private Instant deletedAt;

    @Column(name = "is_deleted", insertable = false, updatable = false)
    private boolean isDeleted;

    @Version
    private Long version;

    private Instant lastLoginAt;

    @OneToOne(mappedBy = "user",
              cascade = {CascadeType.PERSIST, CascadeType.MERGE},
              fetch = FetchType.LAZY,
              optional = false)
    private UserProfile profile;

    @PreRemove
    public void preRemove() {
        this.deletedAt = Instant.now();
    }
    
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    private UserCredential userCredential;
    
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private UserAuthMeta userAuthMeta;
    
    
    

	
//	@Embedded
//	@AttributeOverrides({ @AttributeOverride(name = "dno", column = @Column(name = "door_no")),
//			@AttributeOverride(name = "street", column = @Column(name = "resident_street")),
//			@AttributeOverride(name = "city", column = @Column(name = "resident_city")),
//			@AttributeOverride(name = "state", column = @Column(name = "resident_state")),
//			@AttributeOverride(name = "country", column = @Column(name = "resident_country")),
//			@AttributeOverride(name = "pincode", column = @Column(name = "resident_pincode")) })
//	private Address address;
	
//	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL  , fetch = FetchType.LAZY)
//	private CartModel activeCart;
//
//	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
//	private List<AuditModel> auditLogs;
//	 
//	@OneToMany(mappedBy = "createdBy" , fetch = FetchType.LAZY , cascade = CascadeType.ALL ) 
//	private List<DiscountModel> createdDiscounts ;
//	
//	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//	private List<NotificationModel> notifications;
//	
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<OrderModel> orders;
//	
//	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<ReviewModel> reviews;
//	
//	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<WishListModel> wishlist;
	

}
