package fr.duplessigeoffrey.choptaphoto.ppe_android

// class Photo(val id:Int, val url: String, var estAime: Boolean){
//    // affiche le resultat plus proprement
//    override fun toString(): String {
//        // [identifiant: 1 ===== url: 1.png, identifiant: 2 ===== url: 2.png]
//        return "identifiant: $id ===== url: $url === estAime: $estAime"
//    }
//}

class Photo(val id:Int, val url: String, var likeCount: Int){
    // affiche le resultat plus proprement
    override fun toString(): String {
        // [identifiant: 1 ===== url: 1.png, identifiant: 2 ===== url: 2.png]
        return "identifiant: $id ===== url: $url === estAime: $likeCount"
    }
}