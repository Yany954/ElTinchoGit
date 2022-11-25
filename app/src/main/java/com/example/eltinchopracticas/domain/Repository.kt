package com.example.eltinchopracticas.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eltinchopracticas.models.compras
import com.example.eltinchopracticas.models.entradas
import com.example.eltinchopracticas.models.favoritos
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    fun getEntradasData():LiveData<MutableList<entradas>>{
        val mutableLiveData=MutableLiveData<MutableList<entradas>>()
        //accedemos a la coleccion
        FirebaseFirestore.getInstance().collection("entradas").get().addOnSuccessListener {
            result -> val listData = mutableListOf<entradas>()
            for (document in result){
                //recorremos los libros
                val titulo=document.getString("titulo")
                val precio=document.getString("precio")
                val imagen = document.getString("imagen")
                //no acepta nulos !!
                //acepta nulos ??
                val entrada=entradas(titulo!!,precio!!,imagen)
                //almacenamos en esta lista
                listData.add(entrada)
            }
            mutableLiveData.value=listData

        }
        //retorna la coleccion de entradas
        return mutableLiveData
    }
    fun getDataComprasData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        FirebaseFirestore.getInstance().collection("compras")
            .get().addOnSuccessListener {
                result->
                val listData= mutableListOf<compras>()
                for(document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val imagen = document.getString("imagen")
                    val compra=compras(titulo!!,precio!!,imagen)
                    listData.add(compra)
                }
                mutableData.value=listData
            }
        return mutableData
    }
    fun getDataFavoritosData():LiveData<MutableList<favoritos>>{
        val mutableData=MutableLiveData<MutableList<favoritos>>()
        FirebaseFirestore.getInstance().collection("favoritos")
            .get().addOnSuccessListener {
                    result->
                val listData= mutableListOf<favoritos>()
                for(document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val imagen = document.getString("imagen")
                    val favorito=favoritos(titulo!!,precio!!,imagen)
                    listData.add(favorito)
                }
                mutableData.value=listData
            }
        return mutableData
    }
}