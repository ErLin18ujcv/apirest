package com.example.demo.service;
import com.example.demo.entity.Empleados;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadosService implements IEmpleadosService{

    @Autowired
    private EmpleadosRepository repository;

    @Override
    public Empleados saveEmpleados(Empleados empleados) throws BusinessException {
        try {
            if (empleados.getNombre().isEmpty()){
                throw new BusinessException("El campo no debe estar vacio");
            }if(empleados.getNombre().length()<5){
                throw new BusinessException("El nombre debe tener mas de 10 caracteres");
            }if (empleados.getTelefono().length()<8){
                throw new BusinessException("No debe tener menos de 8 caracteres");
            }
            return repository.save(empleados);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Empleados> saveEmpleados(List<Empleados> empleados) throws BusinessException {
       try {
           for (Empleados empleado:empleados) {
               if(empleado.getNombre().length()<5){
                   throw new BusinessException("El nombre debe tener mas de 10 caracteres");
               }if (empleado.getTelefono().length()<8){
                   throw new BusinessException("No debe tener menos de 8 caracteres");
               }
           }
           return repository.saveAll(empleados);
       }catch (Exception e){
           throw new BusinessException(e.getMessage());
       }
    }

    @Override
    public List<Empleados> getEmpleados() throws BusinessException {
        try {
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Empleados getEmpleadosById(long id) throws BusinessException, NotFoundException {
        Optional<Empleados> opt= null;
        try {
            opt=repository.findById(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("no se encontro el empleado"+id);
        }
        return opt.get();
    }

    @Override
    public Empleados getEmpleadoByNombre(String nombre) throws BusinessException, NotFoundException {
        Optional<Empleados> opt= null;
        try {
            opt=repository.findByNombre(nombre);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("no se encontro el empleado"+nombre);
        }
        return opt.get();
    }

    @Override
    public void deleteEmpleados(long id) throws BusinessException, NotFoundException {
        Optional<Empleados> opt= null;
        try {
            opt=repository.findById(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("no se encontro el empleado"+id);
        }
        else {
            try {
                repository.deleteById(id);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public Empleados updateEmpleados(Empleados empleados) throws BusinessException, NotFoundException {
        Optional<Empleados> opt= null;
        try {
            opt=repository.findById(empleados.getId());
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("no se encontro el empleado"+empleados.getId());
        }
        else {
            try {
                Empleados existingEmpleados =new Empleados();
                existingEmpleados.setId(empleados.getId());
                existingEmpleados.setNombre(empleados.getNombre());
                existingEmpleados.setNoDocumento(empleados.getNoDocumento());
                existingEmpleados.setFechaNacimiento(empleados.getFechaNacimiento());
                existingEmpleados.setTelefono(empleados.getTelefono());
                existingEmpleados.setFechaIngreso(empleados.getFechaIngreso());
                existingEmpleados.setCorreo(empleados.getCorreo());
                existingEmpleados.setDireccion(empleados.getDireccion());
                return repository.save(existingEmpleados);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
