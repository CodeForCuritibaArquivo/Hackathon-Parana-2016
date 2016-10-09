package aghr.olhaobra.POJO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Obra {
    private int ID;
    private int ID_Endereco;
    private Date PrevisaoTermino;


    public Obra(int ID, int Endereco, Date PrevisaoTermino)
    {
        this.ID = ID;
        this.ID_Endereco = Endereco;
        this.PrevisaoTermino = PrevisaoTermino;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEndereco() {
        return ID_Endereco;
    }

    public void setEndereco(int endereco) {
        ID_Endereco = endereco;
    }

    public Date getPrevisaoTermino() {
        return PrevisaoTermino;
    }

    public void setPrevisaoTermino(Date previsaoTermino) {
        PrevisaoTermino = previsaoTermino;
    }

    public static List<Obra> getMockList()
    {
        Obra[] obras = {
                new Obra(1,1, new Date()),
                new Obra(2,1, new Date()),
                new Obra(3,1, new Date()),
                new Obra(4,1, new Date()),
                new Obra(5,1, new Date())
        };
        List<Obra> ret = Arrays.asList(obras);

        return ret;
    }

//    @Override
//    public String toString()
//    {
//        return this.Endereco;
//    }

}
