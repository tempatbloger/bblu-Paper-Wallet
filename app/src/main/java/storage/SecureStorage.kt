package org.bblu.wallet.storage

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager

class SecureStorage(ctx: Context) {
    private val aead: Aead

    init {
        AeadConfig.register()
        aead = AndroidKeysetManager.Builder()
            .withSharedPref(ctx, "bblu_keys", "master")
            .withKeyTemplate(com.google.crypto.tink.aead.AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri("android-keystore://bblu")
            .build()
            .keysetHandle
            .getPrimitive(Aead::class.java)
    }

    fun encrypt(data: ByteArray): ByteArray = aead.encrypt(data, null)
    fun decrypt(data: ByteArray): ByteArray = aead.decrypt(data, null)
}
