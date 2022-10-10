package cat.itb.naimgomez7e5.dam.m06.uf1.TelegramBot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import java.time.Instant
import java.time.format.DateTimeFormatter


fun main() {
    val somedata : MutableMap<Long, String> = mutableMapOf()
       val bot = bot {
            token = "5706262983:AAHn3i_pRGZPxNuGlsSEok2aBrpHa1SeP-0"
            dispatch {
                text {
                    if(text == "hola") {
                        bot.sendMessage(ChatId.fromId(message.chat.id), text = "adéu")
                    }
                }
                command("today") {
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = DateTimeFormatter.ISO_INSTANT.format(
                        Instant.now()).toString() )
                }
                command("store") {

                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "..." )
                    result.fold({
                        somedata.put(message.chat.id, args.joinToString(" "))
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Datos guardados!" )
                    },{
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Ha ocurrido un error" )
                    })
                }
                command("retrieve") {
                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "..." )
                    result.fold({

                        somedata[message.chat.id]?.let { it1 -> bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = it1) }
                    },{
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Ha ocurrido un error" )
                    })
                }
            }
        }
        bot.startPolling()
    }
