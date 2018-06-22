package com.plan111mil.hostservice;

import com.plan111mil.filter.Filter;
import com.plan111mil.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author LMusso
 * @author LTagliretti
 * @author JMontoya
 * @author Georgy
 *
 */
@Entity
public class Housing extends HostService {

    
    private String address;
    private String mail;
    private String phone;
    @OneToMany(mappedBy = "housing")
    private List<Room> rooms;
    @OneToOne
    private User idUser;

    /**
     * Constructor Vacío
     */
    public Housing() {
    }

    /**
     * Método Constructor.
     *
     * @param address (String) Dirección del hospedaje/alojamiento.
     * @param mail (String) E-mail del hospedaje/alojamiento..
     * @param phone (String) Teléfono del hospedaje/alojamiento..
     * @param name (String) Identificador del hospedaje/alojamiento.
     * @param description (String) Descripción del hospedaje/alojamiento..
     * @param capacity (Integer) Capacidad de la habitación.
     * @param hostServiceType (String) Descripción del tipo de habitación.
     * @param user
     */
    public Housing(String address, String mail, String phone, String name, String description, Integer capacity, String hostServiceType, User user) {
        super(name, description, capacity, hostServiceType);
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.idUser = user;
        this.rooms = new ArrayList<>();
    }

    /**
     * Este es un método que comprueba si un hospedaje/alojamiento pasa el
     * criterio de filtrado recibido por parámetro
     *
     * @param filters es la lista de filtros, el hospedaje/alojamiento debe
     * cumplir todos los filtros y también al menos una habitación
     * 
     * @return retorna 'true' solo si el hospedaje/alojamiento cumple todos los
     * filtros y al menos una habitación cumple también todos los filtros
     */
    @Override
    public boolean searchDisp(List<Filter> filters) {
        //en la clase padre se verifica que el hospedaje/alojamiento cumpla.
        if (super.searchDisp(filters)) 
            return true;
        else
            //se recorren las habitaciones
            for(Room room : this.rooms) {
                //se llama al metodo análogo de room pasandole los filtros.
                if (room.searchDisp(filters)) {
                    //si una habitación cumple retorna 'true'.
                    return true;
                }
            }
        //si ninguna cumple es 'false'.
        return false;
    }

    
    /**
     * Permite agregar una nueva habitación la lista.
     *
     * @param r (Room) Nueva habitación en la lista.
     */
    public void addRoom(Room r) {
        this.rooms.add(r);
    }

    
    /**
     * Permite eliminar una habitación a la lista.
     *
     * @param r (Room) Habitación a eliminar.
     */
    public void deleteRoom(Room r) {
        this.rooms.remove(r);
    }


    /**
     *
     * @return Devuelve una descripción completa del hospedaje/alojamiento..
     */
    @Override
    public String toString() {
        return "Hospedaje: " + this.getName() + " | mail: " + this.mail + " | Teléfono: " + this.phone
                + " | Dirección: " + this.address + "\n";
    }

    
    /**
     * @return Devuelve la dirección del hospedaje/alojamiento.
     */
    public String getAddress() {
        return address;
    }

    
    /**
     * Permite asignarle la dirección al hospedaje/alojamiento.
     *
     * @param address (String) Dirección del hospedaje/alojamiento..
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
    /**
     * @return Devuelve el E-mail del hospedaje/alojamiento.
     */
    public String getMail() {
        return mail;
    }

    
    /**
     * Permite asignarle un correo electrónico al hospedaje/alojamiento.
     *
     * @param mail (String) E-mail del hospedaje/alojamiento..
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    
    /**
     * @return Devuelve el número de teléfono del hospedaje/alojamiento
     */
    public String getPhone() {
        return phone;
    }

    
    /**
     * Permite asignar un número de teléfono al hospedaje/alojamiento.
     *
     * @param phone (String) Teléfono del hospedaje/alojamiento.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    /**
     * @return Devuelve un listado de las habitaciones del
     * hospedaje/alojamiento.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    
    /**
     * Permite asignar una lista de habitaciones al hospedaje/alojamiento.
     *
     * @param rooms (List) Lista de habitaciones.
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    
    /**
     * @return the idUser
     */
    public User getIdUser() {
        return idUser;
    }

    
    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

}
