package com.disderm.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


public class Crypto {

//For 512
public static String asHex (byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
        if (((int) buf[i] & 0xff) < 0x10) {
        strbuf.append("0");
        }
        strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
        }

public static String encrypt (String message, String method) {
        String result = message;
        try {
        MessageDigest md = MessageDigest.getInstance(method);
        md.update(message.getBytes());
        result = asHex(md.digest());
        } catch (Exception e) {
        System.out.println ("Crypto.java encrypt() Exception: " + e.getMessage());
        }
        return result;
        }

//****************AES*****************
private SecretKeySpec generateKey(String aesKey){
        SecretKeySpec skeySpec = null;

        try {
        byte[] key = (aesKey).getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit

        skeySpec = new SecretKeySpec(key, "AES");

        } catch (UnsupportedEncodingException e) {
        System.out.println("Crypto.java  generateKey: UnsupportedEncodingException: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
        System.out.println("Crypto.java  generateKey: NoSuchAlgorithmException: " + e.getMessage());
        }

        return skeySpec;
        }

public String encipherAES(String aesKey, String message){
        SecretKeySpec skeySpec = generateKey(aesKey);

        Cipher cipher ;
        byte[] encrypted = null;
        try {
        cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        encrypted = cipher.doFinal((message).getBytes());

        } catch (NoSuchAlgorithmException e) {
        System.out.println("Crypto.java  encipherAES: NoSuchAlgorithmException: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
        System.out.println("Crypto.java  encipherAES: NoSuchPaddingException: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
        System.out.println("Crypto.java  encipherAES: IllegalBlockSizeException: " + e.getMessage());
        } catch (BadPaddingException e) {
        System.out.println("Crypto.java  encipherAES: BadPaddingException: " + e.getMessage());
        } catch (InvalidKeyException e) {
        System.out.println("Crypto.java  encipherAES: InvalidKeyException: " + e.getMessage());
        }

        return Base64.getEncoder().encodeToString(encrypted);
        }

public String decriptAES(String aesKey, String encrypted){
        SecretKeySpec skeySpec = generateKey(aesKey);

        Cipher cipher ;
        byte[] original = null;
        try {
        cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

        } catch (NoSuchAlgorithmException e) {
        System.out.println("Crypto.java  decriptAES: NoSuchAlgorithmException: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
        System.out.println("Crypto.java  decriptAES: IllegalBlockSizeException: " + e.getMessage());
        } catch (InvalidKeyException e) {
        System.out.println("Crypto.java  decriptAES: InvalidKeyException: " + e.getMessage());
        } catch (BadPaddingException e) {
        System.out.println("Crypto.java  decriptAES: BadPaddingException: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
        System.out.println("Crypto.java  decriptAES: NoSuchPaddingException: " + e.getMessage());
        }

        return new String(original);
        }

        }
