package aplicacao.modelos;

import aplicacao.consultas.Cotacao;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;

public class Moeda {
    // Atributos
    @SerializedName("base_code")
    private String codigo;

    @SerializedName("conversion_rates")
    private JsonObject listaTaxaDeConvesoes;

    @SerializedName("time_last_update_utc")
    private String ultimaAtualizacao;

    // Métodos
    public String getCodigo() {
        return codigo;
    }

    public JsonObject getListaTaxaDeConvesoes() {
        return listaTaxaDeConvesoes;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public String getNomeDaMoeda() throws IOException, InterruptedException {
        ArrayList<ArrayList<String>> listaDeMoedas = Cotacao.getMoedasApi();
        String nomeDaMoeda = "";

        for(ArrayList<String> a:listaDeMoedas) {
            if (a.get(0).equals(this.codigo)) {
                nomeDaMoeda = String.valueOf(a.get(1));
            }
        }
        return nomeDaMoeda;
    }

    public Double compararMoedas(Moeda moedaFinal, double valor) {
        return valor * listaTaxaDeConvesoes.get(moedaFinal.getCodigo()).getAsDouble();
    }
}
