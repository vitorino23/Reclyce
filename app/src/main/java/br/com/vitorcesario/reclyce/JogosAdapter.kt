package br.com.vitorcesario.reclyce

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import kotlinx.android.synthetic.main.meu_jogo_item.view.*
import java.security.AccessControlContext
import javax.sql.ConnectionEventListener

class JogoAdapter(private val jogos: List<Jogo>,
                  private val context: Context,
                  val listener: (Jogo) -> Unit) : RecyclerView.Adapter<JogoAdapter.ViewHolder>() {

    //Método que recebe o ViewHolder e a posição da lista.
    //Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogo= jogos[position]
        holder?.let {
            it.bindView(jogo, listener)
        }
    }

    //Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.meu_jogo_item, parent, false)
        return ViewHolder(view)
    }

    //Método que deverá retornar quantos itens há na lista.
    override fun getItemCount(): Int {
        return jogos.size
    }

    /*
    Com o ViewHolder iremos relacionar o layout criado e adicionar os valores a ele*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(jogo: Jogo,
                     listener: (Jogo) -> Unit) = with(itemView) {
            val ivJogo = ivJogo
            val tvNome = tvNome
            val tvAnoLancamento = tvAnoLancamento
            val tvDescricao = tvDescricao

            ivJogo.setImageDrawable(ContextCompat.getDrawable(context, jogo.imagemId))
            tvNome.text = jogo.titulo
            tvAnoLancamento.text = jogo.anoLancamento.toString()
            tvDescricao.text = jogo.descricao

            setOnClickListener { listener(jogo) }
        }
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }
    /*
    * Vale ressaltar que os métodos onCreateViewHolder e onBindViewHolder não são chamados
    * para todos os itens inicialmente, eles são chamados apenas para os itens visíveis para o usuário.
    * Quando o usuário sobe e desce a lista, estes dois métodos são chamados novamente associando a view
    * reciclada ao conteúdo daquela posição que agora será visível.
    * */
}
