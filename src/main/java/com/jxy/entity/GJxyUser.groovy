package com.jxy.entity
import javax.persistence.Column
import javax.persistence.Entity
import javax.validation.constraints.NotNull

@Entity
class GJxyUser extends AbstractEntity  {
    @Column(name = "user_name")
    @NotNull
    String userName
    @Column(name = "user_pass")
    @NotNull
    String userPass
    @Column(name = "user_role")
    String userRole
}
