// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val id: Int, val nome: String){
    companion object {
        
        var ID_COUNT = 1

        fun criar(nome: String): Usuario = Usuario(ID_COUNT++, nome)
    }
    
    override fun equals(other: Any?): Boolean = if(other is Usuario) this.id == other.id else false
    
    
    override fun hashCode(): Int = id.hashCode()
}

data class ConteudoEducacional(val id: Int, var nome: String, val duracao: Int, val nivel: Nivel){
    companion object {
        
      var ID_COUNT = 1

      fun criar(nome: String, duracao: Int = 60, nivel: Nivel = Nivel.BASICO) = ConteudoEducacional(ID_COUNT++, nome, duracao, nivel)
    }
}

data class Formacao(val nome: String, var conteudos: MutableList<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        if(!inscritos.contains(usuario)) 
        	inscritos.add(usuario)
    }
}

fun adicionarAlunos(formacao: Formacao, alunos: MutableList<Usuario>){
     for(aluno in alunos){
        formacao.let {
            it.matricular(aluno)
        }
    }
}

fun main() {
    //TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    //TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
    
    val cursoKotlinIniciante = ConteudoEducacional.criar(nome = "kotlin iniciante", duracao=90, Nivel.BASICO)
    val cursoKotlinIntermediario = ConteudoEducacional.criar(nome = "kotlin intermediario", duracao=120, Nivel.INTERMEDIARIO)
    val cursoKotlinAvancado = ConteudoEducacional.criar(nome = "kotlin avancado", duracao=90, Nivel.AVANCADO)
    
    val cursoFlutterIniciante = ConteudoEducacional.criar(nome = "flutter iniciante", duracao=120, Nivel.BASICO)
    val cursoFlutterIntermediario = ConteudoEducacional.criar(nome = "flutter intermediario", duracao=90, Nivel.INTERMEDIARIO)
    val cursoFlutterAvancado = ConteudoEducacional.criar(nome = "flutter avancado", duracao=110, Nivel.AVANCADO)
    
    val user1 = Usuario.criar("Pedro");
    val user2 = Usuario.criar("Carlos");
    val user3 = Usuario.criar("Augusto");
    val user4 = Usuario.criar("Fabio");
    
    val formacaoKotlin = Formacao(
        nome = "Formacao Kotlin", 
		mutableListOf(cursoKotlinIniciante, cursoKotlinIntermediario, cursoKotlinAvancado)
    )
    
    val formacaoFlutter = Formacao(
        nome = "Formacao Flutter", 
		mutableListOf(cursoFlutterIniciante, cursoFlutterIntermediario, cursoFlutterAvancado)
    )
    
    val alunosKotlin = mutableListOf(user1, user2, user4)
    val alunosFlutter = mutableListOf(user3)
    
    adicionarAlunos(formacaoKotlin, alunosKotlin)
    adicionarAlunos(formacaoFlutter, alunosFlutter)

    println("==========================================")
    println(formacaoKotlin)
    println("Alunos ${formacaoKotlin.inscritos}")
    println("==========================================")
    println(formacaoFlutter)
    println("Alunos ${formacaoFlutter.inscritos}")


}
