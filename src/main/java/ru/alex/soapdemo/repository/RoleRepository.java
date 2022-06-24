package ru.alex.soapdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.soapdemo.model.Role;
import java.math.BigInteger;

@Repository
public interface RoleRepository extends JpaRepository <Role, BigInteger> {

    Role findRoleByName(String name);
}
