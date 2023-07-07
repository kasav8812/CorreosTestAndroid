package com.example.correostestandroid.Module

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.correostestandroid.Adapters.Mail
import com.example.correostestandroid.Adapters.MailEntity
import com.example.correostestandroid.DB.MailDataBase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MailRepository {
    companion object {
        var mailDatabase: MailDataBase? = null
        var mailTableModel: LiveData<List<MailEntity>>? = null

        fun initializeDB(context: Context) : MailDataBase {
            return MailDataBase.getDataseClient(context)
        }

        fun getMails(context: Context) : LiveData<List<MailEntity>>? {
            mailDatabase = initializeDB(context)
            mailTableModel = mailDatabase!!.mailDao().retrieveMails()
            return mailTableModel
        }

        fun deleteMail(context: Context, mEmailId: Int){
            mailDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                mailDatabase!!.mailDao().deleteEmail(mEmailId)
            }
        }

        fun consumingApi(context: Context){
            mailDatabase = initializeDB(context)
            var mListTemp : ArrayList<MailEntity> = ArrayList()

            dummyJSON().forEach {
                mListTemp.add(MailEntity(it.asunto,it.correoEmisor,it.emisor,it.mensaje, it.hora,it.leido,it.destacado, it.spam,false))
            }

            CoroutineScope(IO).launch {
                mailDatabase!!.mailDao().deleteData()
                mailDatabase!!.mailDao().InsertData(mListTemp)
            }
        }

        fun dummyJSON(): List<Mail> {
            val mEmailsJson = "[{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"CarlosSalazar\",\"correoEmisor\":\"csalazar@gmail.com\",\"asunto\":\"TareaSegundoparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"RaulCarreto\",\"correoEmisor\":\"rcarreto@gmail.com\",\"asunto\":\"TareaTercerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"AngelicaMaravilla\",\"correoEmisor\":\"maran@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"ArisbethMartinez\",\"correoEmisor\":\"amartinez@gmail.com\",\"asunto\":\"Tareacuertoparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false},{\"emisor\":\"JuanPerez\",\"correoEmisor\":\"jperez@gmail.com\",\"asunto\":\"Tareaprimerparcial\",\"mensaje\":\"Loremipsumdolorsitamet,consecteturadipiscingelit\",\"hora\":\"10:00am\",\"leido\":false,\"destacado\":false,\"spam\":false}]"
            var gson = Gson()
            val sType = object : TypeToken<List<Mail>>() { }.type
            val mList = gson.fromJson<List<Mail>>(mEmailsJson, sType)
            return mList
        }

    }
}