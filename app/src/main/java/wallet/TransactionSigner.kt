package org.bblu.wallet.wallet

import java.security.MessageDigest

object TransactionSigner {
    fun sign(rawTx: ByteArray, privKey: ByteArray): ByteArray {
        val sha = MessageDigest.getInstance("SHA-256")
        return sha.digest(rawTx + privKey)
    }
}
