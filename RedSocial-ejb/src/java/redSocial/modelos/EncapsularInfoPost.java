/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

/**
 *
 * @author Jesus
 */
public class EncapsularInfoPost {

    private Integer idUsuario;

    private ComentarioGrupo comentarioGrupo;

    private Post post;

    private Grupos grupo;
    
    private Integer idAmigo;
    
    private String emailAmigo;

    public String getEmailAmigo() {
        return emailAmigo;
    }

    public void setEmailAmigo(String emailAmigo) {
        this.emailAmigo = emailAmigo;
    }
    
    

    public Integer getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(Integer idAmigo) {
        this.idAmigo = idAmigo;
    }
    
    

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ComentarioGrupo getComentarioGrupo() {
        return comentarioGrupo;
    }

    public void setComentarioGrupo(ComentarioGrupo comentarioGrupo) {
        this.comentarioGrupo = comentarioGrupo;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }
}
