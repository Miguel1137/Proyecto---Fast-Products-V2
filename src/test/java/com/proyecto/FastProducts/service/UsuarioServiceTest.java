package com.proyecto.FastProducts.service;

import com.proyecto.FastProducts.entity.Usuario;
import com.proyecto.FastProducts.repository.PermisoRepository;
import com.proyecto.FastProducts.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito para simular dependencias
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PermisoRepository permisoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    // ==========================================
    // PRUEBA 1: Inactivar un usuario exitosamente
    // ==========================================
    @Test
    void testInactivarUsuarioExitoso() {
        // 1. Preparamos el escenario (Un usuario activo)
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setActivo(true);

        // 2. Simulamos lo que hace la base de datos
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        // 3. Ejecutamos el método
        boolean resultado = usuarioService.inactivarUsuario(1);

        // 4. Verificamos los resultados
        assertTrue(resultado, "El método debe devolver true");
        assertFalse(usuario.getActivo(), "El usuario debe quedar inactivo (false)");
        verify(usuarioRepository, times(1)).save(usuario); // Verifica que se guardó en la BD
    }

    // ==========================================
    // PRUEBA 2: Intentar inactivar un usuario que no existe
    // ==========================================
    @Test
    void testInactivarUsuarioNoExistente() {
        // Simulamos que la BD devuelve vacío (no encontró el ID)
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        // Ejecutamos el método
        boolean resultado = usuarioService.inactivarUsuario(99);

        // Verificamos que devuelva false y que nunca intente guardar
        assertFalse(resultado, "Debe devolver false si el usuario no existe");
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    // ==========================================
    // PRUEBA 3: Reactivar un usuario
    // ==========================================
    @Test
    void testReactivarUsuarioExitoso() {
        Usuario usuario = new Usuario();
        usuario.setId(2);
        usuario.setActivo(false); // Está inactivo

        when(usuarioRepository.findById(2)).thenReturn(Optional.of(usuario));

        boolean resultado = usuarioService.reactivarUsuario(2);

        assertTrue(resultado, "El método debe devolver true");
        assertTrue(usuario.getActivo(), "El usuario debe quedar activo (true)");
        verify(usuarioRepository, times(1)).save(usuario);
    }

    // ==========================================
    // PRUEBA 4: Verificar que un usuario activo se guarde correctamente al ser creado
    // ==========================================
    @Test
    void testCrearUsuarioActivoPorDefecto() {
        // Creamos un DTO simulado
        com.proyecto.FastProducts.dto.UsuarioDTO dto = new com.proyecto.FastProducts.dto.UsuarioDTO();
        dto.setNombreCompleto("Juan Perez");
        dto.setNombreUsuario("juanp");
        dto.setContrasena("1234");

        // Ejecutamos el método de crear
        usuarioService.crearUsuario(dto);

        // Verificamos que el repositorio haya guardado un usuario
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}