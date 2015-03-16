//
//  CustomTableViewCell.h
//  Project 2
//
//  Created by Jeremiah Bonham on 3/12/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CustomTableViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *itemCellText;
@property (weak, nonatomic) IBOutlet UILabel *itemIpAddressText;
@property (weak, nonatomic) IBOutlet UILabel *itemWirelessText;

@end
