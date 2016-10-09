package aghr.olhaobra.POJO;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cidade {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nome")
    @Expose
    private String nome;
    @SerializedName("Endereco")
    @Expose
    private List<Object> endereco = new ArrayList<Object>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The nome
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     * The Nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     * The endereco
     */
    public List<Object> getEndereco() {
        return endereco;
    }

    /**
     *
     * @param endereco
     * The Endereco
     */
    public void setEndereco(List<Object> endereco) {
        this.endereco = endereco;
    }

}