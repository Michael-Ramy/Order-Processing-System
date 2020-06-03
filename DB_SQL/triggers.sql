use ebookstore;
DELIMITER $$
CREATE TRIGGER non_neg_quantity BEFORE Update ON ebookstore.book FOR EACH ROW
BEGIN
		if new.quantity < 0 then
        set new.quantity = NULL ; 
        End if;
        
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER below_threshold after Update ON ebookstore.book FOR EACH ROW
BEGIN
        if old.quantity > old.threshold then
			if new.quantity < old.threshold then
			Insert  into booksorders values(old.ISBN, NOW(),NOW(),new.quantity);
			End if;
		End if;
        
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER confirm_order before Delete ON ebookstore.booksorders FOR EACH ROW
BEGIN
        -- if old.quantity > old.threshold then
-- 		End if;
		update book set book.quantity = 2 * book.threshold + book.quantity where book.ISBN = old.bookISBN;
        
END $$
DELIMITER ;
