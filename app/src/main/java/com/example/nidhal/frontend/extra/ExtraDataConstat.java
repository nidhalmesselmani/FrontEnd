package com.example.nidhal.frontend.extra;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.itextpdf.text.pdf.PdfAWriter;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nidhal on 01/08/2017.
 */

public class ExtraDataConstat extends AppCompatActivity {

    int id_constat;
    int blesseCheck;

    int m_blesseCheck;

    int d_vh_aCheck;
    int d_vh_bCheck;
    int temoinsCheck;

    @BindView(R.id.m_blesse)
    Switch m_blesse;

    @BindView(R.id.blesse)
    Switch blesse;


    @BindView(R.id.d_vh_a)
    Switch d_vh_a;

    @BindView(R.id.d_vh_b)
    Switch d_vh_b;


    @BindView(R.id.temoins)
    Switch temoins;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.extradataconstat);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_constat = extras.getInt("id_constat");
            //The key argument here must match that used in the other activity

            Toast.makeText(this,String.valueOf(id_constat),Toast.LENGTH_SHORT).show();
        }

        ButterKnife.bind(this);

        blesse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  Toast.makeText(getApplicationContext(),"ischecked"+String.valueOf(isChecked),Toast.LENGTH_SHORT).show();


                if(isChecked)
                    blesseCheck = 1;
                else
                    blesseCheck = 0;
            }
        });
        m_blesse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(),"ischecked"+String.valueOf(isChecked),Toast.LENGTH_SHORT).show();


                if(isChecked)
                    m_blesseCheck = 1;
                else
                    m_blesseCheck = 0;
            }
        });

        d_vh_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(),"ischecked"+String.valueOf(isChecked),Toast.LENGTH_SHORT).show();


                if(isChecked)
                    d_vh_aCheck = 1;
                else
                    d_vh_aCheck = 0;
            }
        });

        d_vh_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(),"ischecked"+String.valueOf(isChecked),Toast.LENGTH_SHORT).show();


                if(isChecked)
                    d_vh_bCheck = 1;
                else
                    d_vh_bCheck = 0;
            }
        });
        temoins.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(),"ischecked"+String.valueOf(isChecked),Toast.LENGTH_SHORT).show();


                if(isChecked)
                    temoinsCheck = 1;
                else
                    temoinsCheck = 0;
            }
        });








    }

    @OnClick(R.id.save)
    public void save() throws FileNotFoundException {


        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i("extra", "Pdf Directory created");

            //Create time stamp
            Date date = new Date() ;
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);




            File myFile = new File(pdfFolder + timeStamp + ".pdf");

            OutputStream output = new FileOutputStream(myFile);
      Document document = new Document() {
          @Override
          public DocumentType getDoctype() {
              return null;
          }

          @Override
          public DOMImplementation getImplementation() {
              return null;
          }

          @Override
          public Element getDocumentElement() {
              return null;
          }

          @Override
          public Element createElement(String tagName) throws DOMException {
              return null;
          }

          @Override
          public DocumentFragment createDocumentFragment() {
              return null;
          }

          @Override
          public Text createTextNode(String data) {
              return null;
          }

          @Override
          public Comment createComment(String data) {
              return null;
          }

          @Override
          public CDATASection createCDATASection(String data) throws DOMException {
              return null;
          }

          @Override
          public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
              return null;
          }

          @Override
          public Attr createAttribute(String name) throws DOMException {
              return null;
          }

          @Override
          public EntityReference createEntityReference(String name) throws DOMException {
              return null;
          }

          @Override
          public NodeList getElementsByTagName(String tagname) {
              return null;
          }

          @Override
          public Node importNode(Node importedNode, boolean deep) throws DOMException {
              return null;
          }

          @Override
          public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
              return null;
          }

          @Override
          public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
              return null;
          }

          @Override
          public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
              return null;
          }

          @Override
          public Element getElementById(String elementId) {
              return null;
          }

          @Override
          public String getInputEncoding() {
              return null;
          }

          @Override
          public String getXmlEncoding() {
              return null;
          }

          @Override
          public boolean getXmlStandalone() {
              return false;
          }

          @Override
          public void setXmlStandalone(boolean xmlStandalone) throws DOMException {

          }

          @Override
          public String getXmlVersion() {
              return null;
          }

          @Override
          public void setXmlVersion(String xmlVersion) throws DOMException {

          }

          @Override
          public boolean getStrictErrorChecking() {
              return false;
          }

          @Override
          public void setStrictErrorChecking(boolean strictErrorChecking) {

          }

          @Override
          public String getDocumentURI() {
              return null;
          }

          @Override
          public void setDocumentURI(String documentURI) {

          }

          @Override
          public Node adoptNode(Node source) throws DOMException {
              return null;
          }

          @Override
          public DOMConfiguration getDomConfig() {
              return null;
          }

          @Override
          public void normalizeDocument() {

          }

          @Override
          public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
              return null;
          }

          @Override
          public String getNodeName() {
              return null;
          }

          @Override
          public String getNodeValue() throws DOMException {
              return null;
          }

          @Override
          public void setNodeValue(String nodeValue) throws DOMException {

          }

          @Override
          public short getNodeType() {
              return 0;
          }

          @Override
          public Node getParentNode() {
              return null;
          }

          @Override
          public NodeList getChildNodes() {
              return null;
          }

          @Override
          public Node getFirstChild() {
              return null;
          }

          @Override
          public Node getLastChild() {
              return null;
          }

          @Override
          public Node getPreviousSibling() {
              return null;
          }

          @Override
          public Node getNextSibling() {
              return null;
          }

          @Override
          public NamedNodeMap getAttributes() {
              return null;
          }

          @Override
          public Document getOwnerDocument() {
              return null;
          }

          @Override
          public Node insertBefore(Node newChild, Node refChild) throws DOMException {
              return null;
          }

          @Override
          public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
              return null;
          }

          @Override
          public Node removeChild(Node oldChild) throws DOMException {
              return null;
          }

          @Override
          public Node appendChild(Node newChild) throws DOMException {
              return null;
          }

          @Override
          public boolean hasChildNodes() {
              return false;
          }

          @Override
          public Node cloneNode(boolean deep) {
              return null;
          }

          @Override
          public void normalize() {

          }

          @Override
          public boolean isSupported(String feature, String version) {
              return false;
          }

          @Override
          public String getNamespaceURI() {
              return null;
          }

          @Override
          public String getPrefix() {
              return null;
          }

          @Override
          public void setPrefix(String prefix) throws DOMException {

          }

          @Override
          public String getLocalName() {
              return null;
          }

          @Override
          public boolean hasAttributes() {
              return false;
          }

          @Override
          public String getBaseURI() {
              return null;
          }

          @Override
          public short compareDocumentPosition(Node other) throws DOMException {
              return 0;
          }

          @Override
          public String getTextContent() throws DOMException {
              return null;
          }

          @Override
          public void setTextContent(String textContent) throws DOMException {

          }

          @Override
          public boolean isSameNode(Node other) {
              return false;
          }

          @Override
          public String lookupPrefix(String namespaceURI) {
              return null;
          }

          @Override
          public boolean isDefaultNamespace(String namespaceURI) {
              return false;
          }

          @Override
          public String lookupNamespaceURI(String prefix) {
              return null;
          }

          @Override
          public boolean isEqualNode(Node arg) {
              return false;
          }

          @Override
          public Object getFeature(String feature, String version) {
              return null;
          }

          @Override
          public Object setUserData(String key, Object data, UserDataHandler handler) {
              return null;
          }

          @Override
          public Object getUserData(String key) {
              return null;
          }
      };

            //Step 2
            PdfAWriter.getInstance;

        }

    }


}
