package ca.jbrains.pos;

interface Catalog {
	Price lookupPrice(String barcode);
	boolean hasBarcode(String barcode);
}