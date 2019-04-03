/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g")
    , @NamedQuery(name = "Grupos.findByIdGrupo", query = "SELECT g FROM Grupos g WHERE g.idGrupo = :idGrupo")
    , @NamedQuery(name = "Grupos.findByNombre", query = "SELECT g FROM Grupos g WHERE g.nombre = :nombre")})
public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "grupos_componentes", joinColumns = {
        @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")}, inverseJoinColumns = {
        @JoinColumn(name = "id_miembro", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo", fetch = FetchType.LAZY)
    private List<ComentarioGrupo> comentarioGrupoList;
    
    @Transient
    private Integer groupSize;

    public Grupos() {
    }

    public Grupos(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupos(Integer idGrupo, String nombre) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<ComentarioGrupo> getComentarioGrupoList() {
        return comentarioGrupoList;
    }

    public void setComentarioGrupoList(List<ComentarioGrupo> comentarioGrupoList) {
        this.comentarioGrupoList = comentarioGrupoList;
    }
    
    public int getGroupSize() {
        return groupSize;
    }
    
    public void setGroupSize() {
        this.groupSize = this.getUsuarioList().size();

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Grupos[ idGrupo=" + idGrupo + " ]";
    }
    
}
