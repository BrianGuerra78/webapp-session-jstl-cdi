package org.bguerra.apiserclet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bguerra.apiserclet.webapp.headers.configs.ProductoServicePrincipal;
import org.bguerra.apiserclet.webapp.headers.configs.Service;
import org.bguerra.apiserclet.webapp.headers.interceptors.Logging;
import org.bguerra.apiserclet.webapp.headers.models.Categoria;
import org.bguerra.apiserclet.webapp.headers.models.Producto;
import org.bguerra.apiserclet.webapp.headers.repositories.CategoriaRepositoryImpl;
import org.bguerra.apiserclet.webapp.headers.repositories.CrudRepository;
import org.bguerra.apiserclet.webapp.headers.repositories.ProductoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@ApplicationScoped
//@Named("defecto")
@Service
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService {
    //private ProductoRepositoryJdbcImpl repositoryJdbc;

    @Inject
    private CrudRepository<Producto> repositoryJdbc;
    @Inject
    private CrudRepository<Categoria> repositoryCategoriaJdbc;

   /* public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(connection);
    }*/

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            //throw new RuntimeException(e);
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            //throw new RuntimeException(e);
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            //throw new RuntimeException(e);
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            //throw new RuntimeException(e);
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            //throw new RuntimeException(e);
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            //throw new RuntimeException(e);
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
