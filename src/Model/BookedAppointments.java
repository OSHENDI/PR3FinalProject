/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "booked_appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookedAppointments.findAll", query = "SELECT b FROM BookedAppointments b WHERE b.status= 'waiting'"),
    @NamedQuery(name = "BookedAppointments.findById", query = "SELECT b FROM BookedAppointments b WHERE b.id = :id"),
    @NamedQuery(name = "BookedAppointments.findByDoctorComment", query = "SELECT b FROM BookedAppointments b WHERE b.doctorComment = :doctorComment"),

    @NamedQuery(name = "BookedAppointments.findByStatus", query = "SELECT b FROM BookedAppointments b WHERE b.status = :status"),

    @NamedQuery(name = "BookedAppointments.getByFirstName", query = "SELECT b FROM BookedAppointments b JOIN b.userId u WHERE u.firstname = :firstName AND b.status = 'waiting'"),
    @NamedQuery(name = "BookedAppointments.getAllWaiting", query = "SELECT b FROM BookedAppointments b WHERE b.status = :status AND b.userId= :patient")
})
public class BookedAppointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "doctor_comment")
    private String doctorComment;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appointments appointmentId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public BookedAppointments() {
    }

    public BookedAppointments(Integer id) {
        this.id = id;
    }

    public BookedAppointments(Integer id, String doctorComment, String status) {
        this.id = id;
        this.doctorComment = doctorComment;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookedAppointments)) {
            return false;
        }
        BookedAppointments other = (BookedAppointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static List<BookedAppointments> getBookedAppointmentsByFirstName(String name) {
        EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();
        TypedQuery<BookedAppointments> query = entityManager.createNamedQuery("BookedAppointments.getByFirstName", BookedAppointments.class);
        query.setParameter("firstName", name);
        List<BookedAppointments> apptsList = query.getResultList();
        entityManager.close();
        return apptsList;
    }

    public static List<BookedAppointments> getBookedAppointments() {
        EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();
        TypedQuery<BookedAppointments> query = entityManager.createNamedQuery("BookedAppointments.findAll", BookedAppointments.class);
        List<BookedAppointments> apptsList = query.getResultList();
        entityManager.close();
        return apptsList;
    }

    public static List<BookedAppointments> getPatientBookedTypeAppointments(Users userInstance, String status) {
        EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();
        TypedQuery<BookedAppointments> query = entityManager.createNamedQuery("BookedAppointments.getAllWaiting", BookedAppointments.class);
        query.setParameter("status", status);
        query.setParameter("patient", userInstance);
        List<BookedAppointments> apptsList = query.getResultList();
        entityManager.close();
        return apptsList;
    }

    @Override
    public String toString() {
        return "" + id;
    }

}
