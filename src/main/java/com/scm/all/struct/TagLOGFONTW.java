package com.scm.all.struct;

public class TagLOGFONTW {
    public  long      lfHeight;
    public  long      lfWidth;
    public  long      lfEscapement;
    public  long      lfOrientation;
    public  long      lfWeight;
    public  byte      lfItalic;
    public  byte      lfUnderline;
    public  byte      lfStrikeOut;
    public  byte      lfCharSet;
    public  byte      lfOutPrecision;
    public  byte      lfClipPrecision;
    public  byte      lfQuality;
    public  byte      lfPitchAndFamily;
    public  String     lfFaceName;

    public TagLOGFONTW() {
    }

    public TagLOGFONTW(long lfHeight, long lfWidth, long lfEscapement, long lfOrientation, long lfWeight, byte lfItalic, byte lfUnderline, byte lfStrikeOut, byte lfCharSet, byte lfOutPrecision, byte lfClipPrecision, byte lfQuality, byte lfPitchAndFamily, String lfFaceName) {
        this.lfHeight = lfHeight;
        this.lfWidth = lfWidth;
        this.lfEscapement = lfEscapement;
        this.lfOrientation = lfOrientation;
        this.lfWeight = lfWeight;
        this.lfItalic = lfItalic;
        this.lfUnderline = lfUnderline;
        this.lfStrikeOut = lfStrikeOut;
        this.lfCharSet = lfCharSet;
        this.lfOutPrecision = lfOutPrecision;
        this.lfClipPrecision = lfClipPrecision;
        this.lfQuality = lfQuality;
        this.lfPitchAndFamily = lfPitchAndFamily;
        this.lfFaceName = lfFaceName;
    }

    @Override
    public String toString() {
        return "TagLOGFONTW{" +
                "lfHeight=" + lfHeight +
                ", lfWidth=" + lfWidth +
                ", lfEscapement=" + lfEscapement +
                ", lfOrientation=" + lfOrientation +
                ", lfWeight=" + lfWeight +
                ", lfItalic=" + lfItalic +
                ", lfUnderline=" + lfUnderline +
                ", lfStrikeOut=" + lfStrikeOut +
                ", lfCharSet=" + lfCharSet +
                ", lfOutPrecision=" + lfOutPrecision +
                ", lfClipPrecision=" + lfClipPrecision +
                ", lfQuality=" + lfQuality +
                ", lfPitchAndFamily=" + lfPitchAndFamily +
                ", lfFaceName='" + lfFaceName + '\'' +
                '}';
    }
}
