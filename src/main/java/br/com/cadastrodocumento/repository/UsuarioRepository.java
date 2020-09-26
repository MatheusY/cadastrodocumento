package br.com.cadastrodocumento.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.cadastrodocumento.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>, UsuarioRepositoryCustom{

	Optional<Usuario> findByUsuario(String usuario);

	Optional<Usuario> findByUsuarioAndSenha(String usuario, String senhaHash);

}
