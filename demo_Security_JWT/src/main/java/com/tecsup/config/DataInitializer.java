package com.tecsup.config;

import com.tecsup.entity.Role;
import com.tecsup.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Crea los roles USER / ADMIN / MODERATOR si no existen.
 * La guia no indica insertarlos manualmente, sin este paso
 * el signup falla con "Rol USER no encontrado".
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        createIfNotExists("USER");
        createIfNotExists("ADMIN");
        createIfNotExists("MODERATOR");
    }

    private void createIfNotExists(String name) {
        if (roleRepository.findByName(name).isEmpty()) {
            Role r = new Role();
            r.setName(name);
            roleRepository.save(r);
            System.out.println("Rol creado: " + name);
        }
    }
}
