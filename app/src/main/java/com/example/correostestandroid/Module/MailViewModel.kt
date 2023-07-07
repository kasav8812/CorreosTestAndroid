package com.example.correostestandroid.Module

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.correostestandroid.Adapters.MailEntity

class MailViewModel : ViewModel() {
    lateinit var liveDataMail: LiveData<List<MailEntity>>

    fun callApi(context: Context) {
        MailRepository.consumingApi(context)
    }

    fun getMails(context: Context) : LiveData<List<MailEntity>> {
        liveDataMail = MailRepository.getMails(context)!!
        return Transformations.map(liveDataMail) {
            it.filter { it ->
                !it.eliminado
            }
            }
    }

    fun deleteMail(context: Context, mEmailId : Int):LiveData<List<MailEntity>>{
        MailRepository.deleteMail(context,mEmailId)
        liveDataMail = MailRepository.getMails(context)!!
        return Transformations.map(liveDataMail) {
            it.filter { it ->
                !it.eliminado
            }
        }
    }

    fun getAllMails(context: Context) : LiveData<List<MailEntity>>? {
        liveDataMail = MailRepository.getMails(context)!!
        return Transformations.map(liveDataMail) {
            it.filter { it ->
                !it.eliminado
            }
        }
    }
    fun filterMails(mPhrase : String, context: Context) : LiveData<List<MailEntity>>? {
        liveDataMail = MailRepository.getMails(context)!!
        return Transformations.map(liveDataMail) {
            it.filter { it ->
                it.emisor.contains(mPhrase) || it.asunto.contains(mPhrase) || it.correoEmisor.contains(mPhrase) && !it.eliminado
            }
        }
    }
}