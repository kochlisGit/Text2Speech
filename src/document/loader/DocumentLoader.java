package document.loader;

import document.entity.Document;

public interface DocumentLoader {
    Document loadDocument(String filePath);
}
