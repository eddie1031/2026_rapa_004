package model.s03;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int balance;

    @OneToOne(
        mappedBy = "member",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Address address;

    private LocalDateTime signupAt = LocalDateTime.now();

    private LocalDateTime lastLoginAt = LocalDateTime.now();

    /*
    생성자
     */
    protected Member() {}

    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;

//        this.signupAt = LocalDateTime.now();
//        this.lastLoginAt = LocalDateTime.now();

    }

    /*
    편의 메서드
     */
    public void setAddress(Address address) {
        this.address = address;

        if ( address != null  ) {
            address.setMember(this);
        }

    }

    /*
    Getter and Setter
     */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

}
