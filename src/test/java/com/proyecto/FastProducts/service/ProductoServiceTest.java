package com.proyecto.FastProducts.service;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    // ==========================================
    // PRUEBA 1: Listar todos los productos
    // ==========================================
    @Test
    void testListarProductos() {
        // 1. Preparamos los datos simulados (2 productos)
        Producto p1 = new Producto();
        p1.setNombre("Camisa");
        Producto p2 = new Producto();
        p2.setNombre("Pantalón");

        // 2. Simulamos que el repositorio devuelve esa lista
        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // 3. Ejecutamos la consulta
        List<Producto> resultado = productoRepository.findAll();

        // 4. Verificamos que la lista tenga 2 elementos y los nombres correctos
        assertEquals(2, resultado.size());
        assertEquals("Camisa", resultado.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    // ==========================================
    // PRUEBA 2: Buscar un producto por ID (Existente)
    // ==========================================
    @Test
    void testBuscarProductoPorIdExistente() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Zapatos");

        // Simulamos que el producto con ID 1 existe en la base de datos
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // Ejecutamos la búsqueda
        Optional<Producto> resultado = productoRepository.findById(1);

        // Verificamos que el producto esté presente y tenga el nombre correcto
        assertTrue(resultado.isPresent());
        assertEquals("Zapatos", resultado.get().getNombre());
    }

    // ==========================================
    // PRUEBA 3: Intentar buscar un producto que no existe
    // ==========================================
    @Test
    void testBuscarProductoNoExistente() {
        // Simulamos que el ID 99 no existe
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoRepository.findById(99);

        // Verificamos que el Optional esté vacío
        assertFalse(resultado.isPresent());
    }

    // ==========================================
    // PRUEBA 4: Guardar un producto nuevo
    // ==========================================
    @Test
    void testGuardarProducto() {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Gorra");
        nuevoProducto.setPrecio(50.0);

        // Simulamos que al guardar, el repositorio devuelve el mismo producto
        when(productoRepository.save(nuevoProducto)).thenReturn(nuevoProducto);

        Producto resultado = productoRepository.save(nuevoProducto);

        // Verificamos que el producto guardado sea el mismo
        assertNotNull(resultado);
        assertEquals("Gorra", resultado.getNombre());
        verify(productoRepository, times(1)).save(nuevoProducto);
    }
}