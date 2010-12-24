package ca.jbrains.pos;

interface Catalog {
	Product lookupProduct(String barcode);
	boolean hasBarcode(String barcode);
}