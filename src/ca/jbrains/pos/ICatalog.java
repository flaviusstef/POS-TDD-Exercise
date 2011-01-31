package ca.jbrains.pos;

interface ICatalog {
	Product lookupProduct(String barcode);
	boolean hasBarcode(String barcode);
}