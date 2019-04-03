/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

import com.google.gson.Gson;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 75)
    @Column(name = "email")
    private String email;
    @ManyToMany(mappedBy = "usuarioList", fetch = FetchType.LAZY)
    private List<Grupos> gruposList;
    //atributo para la lista de amigos
    @JoinTable(name = "amigos", joinColumns = {
        @JoinColumn(name = "id_usuario1", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario2", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Usuario> amigosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublicador", fetch = FetchType.LAZY)
    private List<Post> postList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAutor", fetch = FetchType.LAZY)
    private List<ComentarioGrupo> comentarioGrupoList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String nombre, String apellidos, String password, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Grupos> getGruposList() {
        return gruposList;
    }

    public void setGruposList(List<Grupos> gruposList) {
        this.gruposList = gruposList;
    }

    @XmlTransient
    public List<Usuario> getAmigosList() {
        return amigosList;
    }

    public void setUsuarioList(List<Usuario> amigosList) {
        this.amigosList = amigosList;
    }


    @XmlTransient
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @XmlTransient
    public List<ComentarioGrupo> getComentarioGrupoList() {
        return comentarioGrupoList;
    }

    public void setComentarioGrupoList(List<ComentarioGrupo> comentarioGrupoList) {
        this.comentarioGrupoList = comentarioGrupoList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Usuario[ id=" + id + " ]";
    }
    
    public static String toJson(Usuario user) {
        Gson gson = new Gson();
        for(Usuario u: user.getAmigosList()) {
            u.setGruposList(null);
            u.setUsuarioList(null);
            u.setComentarioGrupoList(null);
            u.setPostList(null);
        }
        for (Post p: user.getPostList()) {
            p.setIdPublicador(null);
        }
        for (ComentarioGrupo c: user.getComentarioGrupoList()) {
            c.setIdAutor(null);
            c.setIdGrupo(null);
        }
        for (Grupos g: user.getGruposList()) {
            g.setGroupSize();
            g.setUsuarioList(null);
//            for (Usuario u: g.getUsuarioList())  {
//                if (!u.equals(user)) {
//                    u.setComentarioGrupoList(null);
//                    u.setGruposList(null);
//                    u.setPostList(null);
//                    u.setUsuarioList(null);
//                }
//            }
            for (ComentarioGrupo c: g.getComentarioGrupoList()) {
                c.setIdAutor(null);
                c.setIdGrupo(null);
            }
        }
        String userJson = gson.toJson(user);
        return userJson;
    }
    
}
