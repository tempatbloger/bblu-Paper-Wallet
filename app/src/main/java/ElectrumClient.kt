package org.bblu.wallet.electrum

import java.io.*
import java.net.Socket
import org.json.JSONObject

class ElectrumClient(
    private val host: String = "electrumx.bitcoin-blu.org",
    private val port: Int = 50001
) {
    private lateinit var socket: Socket
    private lateinit var reader: BufferedReader
    private lateinit var writer: BufferedWriter
    private var id = 0

    fun connect() {
        socket = Socket(host, port)
        reader = socket.getInputStream().bufferedReader()
        writer = socket.getOutputStream().bufferedWriter()
    }

    fun call(method: String, params: List<Any>): JSONObject {
        val req = JSONObject().apply {
            put("jsonrpc", "2.0")
            put("id", ++id)
            put("method", method)
            put("params", params)
        }
        writer.write(req.toString() + "\n")
        writer.flush()

        return JSONObject(reader.readLine())
    }

    fun getBalance(scriptHash: String): JSONObject =
        call("blockchain.scripthash.get_balance", listOf(scriptHash))
}
