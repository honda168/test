package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 掲示板の投稿内容を保持するレコードクラス
 */
public class Record {
    // 日時フォーマット（例: 2023/12/31 23:59:59）
    private static final DateTimeFormatter fmt = 
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private LocalDateTime datetime; // 投稿日時
    private String name;            // 投稿者名
    private String contents;        // 投稿内容

    /**
     * コンストラクタ
     * @param name 投稿者名
     * @param contents 投稿内容
     */
    public Record(String name, String contents) {
        this.name = name;
        this.contents = contents;
        this.datetime = LocalDateTime.now(); // 投稿日時を自動設定
    }

    /**
     * フォーマットされた投稿日時を取得
     * @return フォーマット済み日時文字列
     */
    public String getDatetime() {
        return datetime.format(fmt);
    }

    /**
     * 投稿者名を取得
     * @return 投稿者名
     */
    public String getName() {
        return name;
    }

    /**
     * 投稿内容を取得（改行を<br>に変換）
     * ※HTMLとして出力する場合はXSSに注意！
     * ※信頼できない内容の場合、th:utextではなくth:textを使うこと
     * @return 改行を<br>に変換した投稿内容
     */
    public String getContents() {
        // 改行をHTMLの改行タグに変換
        return contents.replaceAll("\n", "<br>");
    }
    
    // コンソールに表示する用
    public String toString() {
        return "Record{name='" + name + "', contents='" + contents + "'}"; // 例
    }

}
