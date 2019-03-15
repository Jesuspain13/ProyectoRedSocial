/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "amistades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amistades.findAll", query = "SELECT a FROM Amistades a")
    , @NamedQuery(name = "Amistades.findByAmistadesid", query = "SELECT a FROM Amistades a WHERE a.amistadesid = :amistadesid")})
public class Amistades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "amistadesid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer amistadesid;
    @JoinColumn(name = "id_usuario1", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario1;
    @JoinColumn(name = "id_usuario2", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario2;

    public Amistades() {
    }

    public Amistades(Integer amistadesid) {
        this.amistadesid = amistadesid;
    }

    public Integer getAmistadesid() {
        return amistadesid;
    }

    public void setAmistadesid(Integer amistadesid) {
        this.amistadesid = amistadesid;
    }

    public Usuario getIdUsuario1() {
        return idUsuario1;
    }

    public void setIdUsuario1(Usuario idUsuario1) {
        this.idUsuario1 = idUsuario1;
    }

    public Usuario getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(Usuario idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amistadesid != null ? amistadesid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amistades)) {
            return false;
        }
        Amistades other = (Amistades) object;
        if ((this.amistadesid == null && other.amistadesid != null) || (this.amistadesid != null && !this.amistadesid.equals(other.amistadesid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Amistades[ amistadesid=" + amistadesid + " ]";
    }
    
}
