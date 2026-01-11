package org.bblu.wallet.crypto

import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.bouncycastle.crypto.digests.SHA512Digest
import org.bouncycastle.crypto.params.KeyParameter

object Bip39 {
    fun mnemonicToSeed(mnemonic: String, passphrase: String = ""): ByteArray {
        val gen = PKCS5S2ParametersGenerator(SHA512Digest())
        gen.init(mnemonic.toByteArray(), ("mnemonic$passphrase").toByteArray(), 2048)
        return (gen.generateDerivedParameters(512) as KeyParameter).key
    }
}
