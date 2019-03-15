/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    , @NamedQuery(name = "Grupos.findByIdgrupos", query = "SELECT g FROM Grupos g WHERE g.idgrupos = :idgrupos")
    , @NamedQuery(name = "Grupos.findByNombregrupo", query = "SELECT g FROM Grupos g WHERE g.nombregrupo = :nombregrupo")})
public class Grupos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupal")
    private Collection<ComentariosGrupos> comentariosGruposCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupos")
    private Integer idgrupos;
    @Size(max = 45)
    @Column(name = "nombregrupo")
    private String nombregrupo;
    @JoinTable(name = "grupos_usuarios", joinColumns = {
        @JoinColumn(name = "id_grupos", referencedColumnName = "idgrupos")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idGrupal")
    private List<ComentariosGrupos> comentariosList;

    public List<ComentariosGrupos> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<ComentariosGrupos> comentariosList) {
        this.comentariosList = comentariosList;
}

    public Grupos() {
    }

    public Grupos(Integer idgrupos) {
        this.idgrupos = idgrupos;
    }

    public Integer getIdgrupos() {
        return idgrupos;
    }

    public void setIdgrupos(Integer idgrupos) {
        this.idgrupos = idgrupos;
    }

    public String getNombregrupo() {
        return nombregrupo;
    }

    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupos != null ? idgrupos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.idgrupos == null && other.idgrupos != null) || (this.idgrupos != null && !this.idgrupos.equals(other.idgrupos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Grupos[ idgrupos=" + idgrupos + " ]";
    }

    @XmlTransient
    public Collection<ComentariosGrupos> getComentariosGruposCollection() {
        return comentariosGruposCollection;
    }

    public void setComentariosGruposCollection(Collection<ComentariosGrupos> comentariosGruposCollection) {
        this.comentariosGruposCollection = comentariosGruposCollection;
    }
    
}
