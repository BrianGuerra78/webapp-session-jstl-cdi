package org.bguerra.apiserclet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bguerra.apiserclet.webapp.headers.configs.Service;
import org.bguerra.apiserclet.webapp.headers.models.Usuario;
import org.bguerra.apiserclet.webapp.headers.repositories.UsuarioRepository;
import org.bguerra.apiserclet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

//@ApplicationScoped
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    /*public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }*/
    @Inject
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
