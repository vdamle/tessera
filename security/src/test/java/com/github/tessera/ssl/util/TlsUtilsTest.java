package com.github.tessera.ssl.util;

import org.bouncycastle.operator.OperatorCreationException;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TlsUtilsTest {

    private static final String FILE = "test-keystore";
    private static final String PASSWORD ="quorum";
    private static final String ALIAS = "tessera";

    Path privateKeyFile = Paths.get(FILE);

    @After
    public void tearDown() {
        assertThat(Files.exists(privateKeyFile)).isFalse();
    }

    @Test
    public void testGenerateKeys() throws OperatorCreationException, InvalidKeyException, NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, CertificateException, KeyStoreException {

        assertThat(Files.exists(privateKeyFile)).isFalse();

        TlsUtils.create().generateKeyStoreWithSelfSignedCertificate(privateKeyFile,PASSWORD);

        assertThat(Files.exists(privateKeyFile)).isTrue();

        //Read keystore from created file
        final KeyStore keyStore = KeyStore.getInstance("JKS");

        try (InputStream in = Files.newInputStream(privateKeyFile)) {
            keyStore.load(in, PASSWORD.toCharArray());
        }
        Certificate certificate = keyStore.getCertificate(ALIAS);

        assertThat(certificate).isNotNull();
        assertThat(certificate.getPublicKey()).isNotNull();

        Files.deleteIfExists(privateKeyFile);

    }
}
