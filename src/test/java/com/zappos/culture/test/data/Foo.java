package com.zappos.culture.test.data;

import com.zappos.json.util.Strings;

public class Foo {
  String a = randomStringWithEscape(32);
  String b = randomStringWithEscape(32);
  String c = randomStringWithEscape(32);
  String d = randomStringWithEscape(32);
  String e = randomStringWithEscape(32);
  String f = randomStringWithEscape(32);
  String g = randomStringWithEscape(32);
  String h = randomStringWithEscape(32);
  String i = randomStringWithEscape(32);
  String j = randomStringWithEscape(32);
  String k = randomStringWithEscape(32);
  String l = randomString(32);
  String m = randomString(32);
  String n = randomString(32);
  String o = randomString(32);
  String p = randomString(32);
  String q = randomString(32);
  String r = randomString(32);
  String s = randomString(32);
  String t = randomString(32);
  String u = randomString(32);
  String v = randomString(32);
  String w = randomString(32);
  String x = randomString(32);
  String y = randomString(32);
  String z = randomString(32);

  private static final char randomChars[] = new char[26 + 10 + 2];

  static {
    /**
     * A-Z,1-9,", and \
     */
    int c = 0;
    for (int i = 65; i <= 90; i++) {
      randomChars[c] = (char) i;
      c++;
    }
    for (int i = 48; i <= 57; i++) {
      randomChars[c] = (char) i;
      c++;
    }
    randomChars[c] = '"';
    randomChars[++c] = '\\';
  }

  public static String randomStringWithEscape(int n) {
    return Strings.random(n, randomChars);
  }

  public static String randomString(int n) {
    return Strings.randomAlphabetic(n);
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }

  public String getC() {
    return c;
  }

  public void setC(String c) {
    this.c = c;
  }

  public String getD() {
    return d;
  }

  public void setD(String d) {
    this.d = d;
  }

  public String getE() {
    return e;
  }

  public void setE(String e) {
    this.e = e;
  }

  public String getF() {
    return f;
  }

  public void setF(String f) {
    this.f = f;
  }

  public String getG() {
    return g;
  }

  public void setG(String g) {
    this.g = g;
  }

  public String getH() {
    return h;
  }

  public void setH(String h) {
    this.h = h;
  }

  public String getI() {
    return i;
  }

  public void setI(String i) {
    this.i = i;
  }

  public String getJ() {
    return j;
  }

  public void setJ(String j) {
    this.j = j;
  }

  public String getK() {
    return k;
  }

  public void setK(String k) {
    this.k = k;
  }

  public String getL() {
    return l;
  }

  public void setL(String l) {
    this.l = l;
  }

  public String getM() {
    return m;
  }

  public void setM(String m) {
    this.m = m;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public String getO() {
    return o;
  }

  public void setO(String o) {
    this.o = o;
  }

  public String getP() {
    return p;
  }

  public void setP(String p) {
    this.p = p;
  }

  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public String getR() {
    return r;
  }

  public void setR(String r) {
    this.r = r;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  public String getT() {
    return t;
  }

  public void setT(String t) {
    this.t = t;
  }

  public String getU() {
    return u;
  }

  public void setU(String u) {
    this.u = u;
  }

  public String getV() {
    return v;
  }

  public void setV(String v) {
    this.v = v;
  }

  public String getW() {
    return w;
  }

  public void setW(String w) {
    this.w = w;
  }

  public String getX() {
    return x;
  }

  public void setX(String x) {
    this.x = x;
  }

  public String getY() {
    return y;
  }

  public void setY(String y) {
    this.y = y;
  }

  public String getZ() {
    return z;
  }

  public void setZ(String z) {
    this.z = z;
  }

}