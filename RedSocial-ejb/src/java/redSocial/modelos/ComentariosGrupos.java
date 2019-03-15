/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "comentarios_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentariosGrupos.findAll", query = "SELECT c FROM ComentariosGrupos c")
    , @NamedQuery(name = "ComentariosGrupos.findByIdComentariogrupo", query = "SELECT c FROM ComentariosGrupos c WHERE c.idComentariogrupo = :idComentariogrupo")
    , @NamedQuery(name = "ComentariosGrupos.findByFecha", query = "SELECT c FROM ComentariosGrupos c WHERE c.fecha = :fecha")})
public class ComentariosGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentariogrupo")
    private Integer idComentariogrupo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_publicador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idPublicador;
    @JoinColumn(name = "id_grupal", referencedColumnName = "idgrupos")
    @ManyToOne(optional = false)
    private Grupos idGrupal;

    public ComentariosGrupos() {
    }

    public ComentariosGrupos(Integer idComentariogrupo) {
        this.idComentariogrupo = idComentariogrupo;
    }

    public ComentariosGrupos(Integer idComentariogrupo, String comentario) {
        this.idComentariogrupo = idComentariogrupo;
        this.comentario = comentario;
    }

    public Integer getIdComentariogrupo() {
        return idComentariogrupo;
    }

    public void setIdComentariogrupo(Integer idComentariogrupo) {
        this.idComentariogrupo = idComentariogrupo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(Usuario idPublicador) {
        this.idPublicador = idPublicador;
    }

    public Grupos getIdGrupal() {
        return idGrupal;
    }

    public void setIdGrupal(Grupos idGrupal) {
        this.idGrupal = idGrupal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentariogrupo != null ? idComentariogrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentariosGrupos)) {
            return false;
        }
        ComentariosGrupos other = (ComentariosGrupos) object;
        if ((this.idComentariogrupo == null && other.idComentariogrupo != null) || (this.idComentariogrupo != null && !this.idComentariogrupo.equals(other.idComentariogrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.ComentariosGrupos[ idComentariogrupo=" + idComentariogrupo + " ]";
    }
    
}
